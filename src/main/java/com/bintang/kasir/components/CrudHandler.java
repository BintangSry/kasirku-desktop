package com.bintang.kasir.components;

public interface CrudHandler {
    void onAdd();
    void onEdit(int id);
    void onDelete(int id);
}
