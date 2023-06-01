package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AviaSoulsTest {

    Ticket ticket1 = new Ticket("Новосибирск", "Москва", 16_000, 8, 12);
    Ticket ticket2 = new Ticket("Новосибирск", "Москва", 12_000, 8, 13);
    Ticket ticket3 = new Ticket("Новосибирск", "Красноярск", 8_000, 5, 7);
    Ticket ticket4 = new Ticket("Новосибирск", "Москва", 25_000, 8, 11);
    Ticket ticket5 = new Ticket("Новосибирск", "Алания", 30_000, 3, 8);

    AviaSouls aviaSouls = new AviaSouls();
    TicketTimeComparator timeComparator = new TicketTimeComparator();

    @BeforeEach
    public void add() {
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
    }

    @Test
    public void shouldSort() {
        Ticket[] tickets = aviaSouls.findAll();
        Arrays.sort(tickets);

        Ticket[] expected = {ticket3, ticket2, ticket1, ticket4, ticket5};
        Ticket[] actual = aviaSouls.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void compareToTestIfLess() {
        int expected = -1;
        int actual = ticket1.compareTo(ticket4);

        assertEquals(expected, actual);
    }

    @Test
    public void compareToTestIfMore() {
        int expected = 1;
        int actual = ticket5.compareTo(ticket3);

        assertEquals(expected, actual);
    }

    @Test
    public void compareToTestIfEqual() {
        Ticket ticket1 = new Ticket("Новосибирск", "Москва", 12_000, 8, 12);
        Ticket ticket2 = new Ticket("Москва", "Новосибирск", 12_000, 8, 13);

        int expected = 0;
        int actual = ticket1.compareTo(ticket2);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfOneMatch() {
        Ticket[] expected = {ticket5};
        Ticket[] actual = aviaSouls.search("Новосибирск", "Алания");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfNotOneMatchAndSort() {
        Ticket[] expected = {ticket2, ticket1, ticket4};
        Ticket[] actual = aviaSouls.search("Новосибирск", "Москва");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfNoMatches() {
        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.search("Сочи", "Казань");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void comparatorTestIfLess() {
        int expected = -1;
        int actual = timeComparator.compare(ticket3, ticket1);

        assertEquals(expected, actual);
    }

    @Test
    public void comparatorTestIfMore() {
        int expected = 1;
        int actual = timeComparator.compare(ticket4, ticket3);

        assertEquals(expected, actual);
    }

    @Test
    public void comparatorTestIfEqual() {
        int expected = 0;
        int actual = timeComparator.compare(ticket2, ticket5);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByIfOneMatch() {
        Ticket[] expected = {ticket3};
        Ticket[] actual = aviaSouls.searchAndSortBy("Новосибирск", "Красноярск", timeComparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByIfNotOneMatch() {
        Ticket[] expected = {ticket4, ticket1, ticket2};
        Ticket[] actual = aviaSouls.searchAndSortBy("Новосибирск", "Москва", timeComparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchAndSortByIfNoMatches() {
        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.searchAndSortBy("Астрахань", "Владивосток", timeComparator);

        assertArrayEquals(expected, actual);
    }
}
