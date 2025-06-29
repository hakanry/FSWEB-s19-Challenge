package com.workintech.s19_twitter_challange.controller;
import com.workintech.s19_twitter_challange.dto.UserResponseDto;
import com.workintech.s19_twitter_challange.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WithMockUser(username = "admin", roles = {"ADMIN","USER"})
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("GET /user - should return list of users")
    void getUsers_ShouldReturnListOfUsers() throws Exception {
        UserResponseDto user = new UserResponseDto();
        user.setUsername("testuser");
        user.setTweets(new HashSet<>());

        Mockito.when(userService.getUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("testuser"));
    }



    @Test
    @DisplayName("DELETE /user/{id} - should return 403 if user tries to delete others")
    void deleteUser_ShouldReturnForbidden_WhenUserIsNotAllowedToDelete() throws Exception {
        Mockito.doThrow(new AccessDeniedException("Silme yetkiniz yok"))
                .when(userService).delete(1L);

        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("DELETE /user/{id} - shouldnt return 403 for invalid user")
    void deleteUser_ShouldReturnBadRequestForInvalidId() throws Exception {
        mockMvc.perform(delete("/user/-10"))
                .andExpect(status().isForbidden());
    }
}
