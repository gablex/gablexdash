/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.interfac;

import com.mspace1.model.users;
import java.util.List;


/**
 *
 * @author mspace
 */
public interface usersinterfc {

    public void saveuser(users user);
     public users getUser(String username, String password);
     public void update(users user);
       public void remove(users user);
       public List<users>list();
       public users getbyid(long id);
  }
