package com.log.service;

import com.log.model.user;

public interface UserService {
      public user getUser(String user_string_id);
      public int getUserIndex(String user_string_id);
      user selectByPrimaryKey(Integer id);
}
