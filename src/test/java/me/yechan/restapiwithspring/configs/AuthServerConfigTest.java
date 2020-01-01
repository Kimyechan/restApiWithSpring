package me.yechan.restapiwithspring.configs;

import me.yechan.restapiwithspring.accounts.Account;
import me.yechan.restapiwithspring.accounts.AccountRole;
import me.yechan.restapiwithspring.accounts.AccountService;
import me.yechan.restapiwithspring.common.BaseControllerTest;
import me.yechan.restapiwithspring.common.TestDescription;
import org.jboss.jandex.JandexAntTask;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Test
    @TestDescription("인증 토큰을 발급 받는 테스트")
    public void getAuthToken() throws Exception {
        String username = "keeun@email.com";
        String password = "keesun";
        Account keesun = Account.builder()
                .email(username)
                .password(password)
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();
        this.accountService.saveAccount(keesun);

        String clientId = "myApp";
        String clientSecret = "pass";

        this.mockMvc.perform(post("/oauth/token")
                    .with(httpBasic(clientId, clientSecret))
                    .param("username", username)
                    .param("password", password)
                    .param("grant_type", "password")
                    )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists());
    }


//    @Test
//    @TestDescription("인증 토큰을 발급 받는 테스트")
//    public void getAuthToken() throws Exception {
//        String username = "keeun@email.com";
//        String password = "keesun";
//        Account keesun = Account.builder()
//                .email(username)
//                .password(password)
//                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
//                .build();
//        this.accountService.saveAccount(keesun);
//
//        String clientId = "myApp";
//        String clientSecret = "pass";
//
//        this.mockMvc.perform(post("/oauth/token")
//                .with(httpBasic(clientId, clientSecret))
//                .param("username", username)
//                .param("password", password)
//                .param("grant_type", "password"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("access_token").exists());
//    }
}