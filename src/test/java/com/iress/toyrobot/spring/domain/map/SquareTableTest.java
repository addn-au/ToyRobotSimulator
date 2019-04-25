package com.iress.toyrobot.spring.domain.map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;

import static com.iress.toyrobot.spring.domain.map.SquareTable.DEFAULT_TOP_SIZE;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SquareTableTest {
    private SquareMap squareTable;

    @Before
    public void setup() {
        squareTable = new SquareTable(DEFAULT_TOP_SIZE);
    }

    @Test
    public void shouldSquareTableBeValid() {
        assertThat(squareTable.isValid(), is(true));
    }

    @Test
    public void shouldEmptySquareTableBeInvalid() {
        SquareMap emptySquareTable = new SquareTable();
        assertThat(emptySquareTable.isValid(), is(false));
    }

    @Test
    public void shouldSquareTableSizeBeOfExpectedNumber() {
        assertThat(squareTable.getSize(), is(DEFAULT_TOP_SIZE));
    }

    @Test
    public void shouldAPointBeInBoundOfTheSquareTable() {
        assertThat(squareTable.isInBound(new Point(5,5)), is(true));
    }

    @Test
    public void shouldAPointNotBeInBoundOfTheSquareTable() {
        assertThat(squareTable.isInBound(new Point(5,7)), is(false));
    }
}
