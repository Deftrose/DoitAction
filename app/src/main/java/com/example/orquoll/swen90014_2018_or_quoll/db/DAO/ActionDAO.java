package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

public interface ActionDAO {
    public void insert(Action newAction);

    public Action[] display();


}
