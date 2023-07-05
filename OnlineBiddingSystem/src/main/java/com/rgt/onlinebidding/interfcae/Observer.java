package com.rgt.onlinebidding.interfcae;

import com.rgt.onlinebidding.entity.Item;

public interface Observer 
{
    void update(Item item , Observer observer);	
}
