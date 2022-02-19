package br.ce.wcaquino.taskbackend.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateUtilsTest {

    @Test
    public void deveRetornarTrueQuandoForNoPresente() {
        boolean condition = DateUtils.isEqualOrFutureDate(LocalDate.now());

        Assert.assertTrue(condition);
    }

    @Test
    public void deveRetornarTrueQuandoForNoFuturo() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        boolean condition = DateUtils.isEqualOrFutureDate(tomorrow);

        Assert.assertTrue(condition);
    }

    @Test
    public void deveRetornarFalsoQuandoForNoPassado() {
        LocalDate tomorrow = LocalDate.now().minusDays(1);
        boolean condition = DateUtils.isEqualOrFutureDate(tomorrow);

        Assert.assertFalse(condition);
    }
}