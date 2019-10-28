package main;

import engine.DIEngine;
import res.Second;
import res.Simple;

import java.lang.annotation.Annotation;

public class Main {

    public static void main(String[] args) {

        DIEngine die = new DIEngine();
        die.init(Second.class);


    }
}
