

package com.account.demo.mapper;

import com.account.demo.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface AccountMapper {

    @Update("update `himly_dubbo_account` set us_wallet = us_wallet + #{us_wallet}, cny_wallet = cny_wallet + " +
            "#{cny_wallet} where us_wallet >= #{us_wallet} and cny_wallet >= #{cny_wallet} and id = #{id}")
    boolean payment(Account account);


    @Select("select * from himly_dubbo_account where id = #{id}")
    Account queryOne(Account account);
}
