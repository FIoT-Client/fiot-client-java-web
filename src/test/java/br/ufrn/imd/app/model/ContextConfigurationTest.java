package br.ufrn.imd.app.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ContextConfigurationTest {

    private ContextConfiguration config;

    @Before
    public void setUp() {
        this.config = new ContextConfiguration();
    }

    @Test
    public void shouldHaveOrionAddress() {
        config.setOrionHost("127.0.0.1");

        Assert.assertThat(config.getOrionHost(), is(equalTo("127.0.0.1")));
    }

    @Test
    public void shouldHaveOrionPort() {
        config.setOrionPort(1026);

        Assert.assertThat(config.getOrionPort(), is(equalTo(1026)));
    }

    @Test
    public void shouldComputeOrionFullAddress() {
        config.setOrionHost("localhost");
        config.setOrionPort(1026);


        Assert.assertThat(config.getOrionFullAddress(), is(equalTo("localhost:1026")));

    }
}
