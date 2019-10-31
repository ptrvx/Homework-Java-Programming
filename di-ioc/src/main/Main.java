package main;

import engine.DIEngine;
import res.InterTest;
import res.Second;
import res.Simple;
import res.Third;

import java.lang.annotation.Annotation;

public class Main {

    public static void main(String[] args) {

        DIEngine die = new DIEngine();


        Third a = (Third)die.init(Third.class);
        Third b = (Third)die.init(Third.class);

    }
}
