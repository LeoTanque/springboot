package com.example.pincha.mapper;

public interface IMapper <I, O> {
    public O map(I in);
}
