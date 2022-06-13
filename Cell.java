package com.dominatingKnights;

public class Cell {
    private int status;              // 0 - empty, 1 - knight placed
    private boolean attacked;


    public Cell() {
        this.status = 0;
        this.attacked = false;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

}