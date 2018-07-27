/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.interfac;

import com.mspace1.model.Tuseraddressbook;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mspace
 */
public interface tuseraddressbook {
    public List<Tuseraddressbook> getgroupsimpl(String sql1);
    public List<Tuseraddressbook> getgroups2impl(long id);
       public List<Tuseraddressbook> alluser(String groupnamex);
}
