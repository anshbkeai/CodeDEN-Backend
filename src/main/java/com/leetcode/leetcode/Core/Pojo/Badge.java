package com.leetcode.leetcode.Core.Pojo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Badge {

    @Id
    private  String  badge_id;
    private  String  icon_url;
 
    private  String badge_name;
    private  String  badge_desc; //  this  the   badge  Description  

    @ManyToMany(mappedBy = "badges_earned")
    private  List<Coder>  badge_earnedbyUser;
}
