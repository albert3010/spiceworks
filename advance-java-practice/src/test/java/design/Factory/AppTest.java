package design.Factory;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AppTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }
    @Test
    public void test() throws IOException {
        String[] args={"hello","hei"};
        App.main(args);
    }

}