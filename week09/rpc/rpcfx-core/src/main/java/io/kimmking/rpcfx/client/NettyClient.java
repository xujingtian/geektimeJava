package io.kimmking.rpcfx.client;

import com.google.common.base.Charsets;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class NettyClient {
    static String host = "";
    static String port = "";
    static String url = "";
    static String resp = "";

    public NettyClient(String url, String host, String port) {
        this.url = url;
        this.port = port;
        this.host = host;
    }

    public String send(String content) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) {
                            channel.pipeline().addLast(new HttpResponseDecoder());
                            channel.pipeline().addLast(new HttpRequestEncoder());
                            channel.pipeline().addLast(new HttpObjectAggregator(65535));
                            channel.pipeline().addLast(new OutputResultHandler(content));
                        }
                    });
            ChannelFuture future = b.connect(host, Integer.parseInt(port));
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resp;
    }

    private static class OutputResultHandler extends SimpleChannelInboundHandler {
        private String content;

        public OutputResultHandler(String content) {
            this.content = content;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws URISyntaxException {
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST,
                    new URI(url).toASCIIString(),
                    Unpooled.wrappedBuffer(content.getBytes(StandardCharsets.UTF_8)));
            request.headers().set(HttpHeaders.Names.HOST, host);
            request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            request.headers().set(HttpHeaders.Names.CONTENT_TYPE, HttpHeaders.Values.APPLICATION_JSON);
            request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());

            ctx.channel().writeAndFlush(request).addListener(new ChannelFutureListener() {

                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    future.get();

                }
            });
        }

        @Override
        protected void channelRead0(ChannelHandlerContext context, Object msg) throws Exception {
            if (msg instanceof HttpResponse) {
                HttpResponse response = (HttpResponse) msg;
            }
            if (msg instanceof HttpContent) {
                HttpContent content = (HttpContent) msg;
                ByteBuf byteBuffer = content.content();
                resp = byteBuffer.toString(Charsets.UTF_8);

                context.close();
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.channel().close();
        }
    }

}
