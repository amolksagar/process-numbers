package com.lexisnexis.processnumbers.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(SpringExtension.class)
class ProcessNumberTest {

  @InjectMocks
  private ProcessNumberService processNumberService;

  @Test
  void testInteger() {
    int smallInteger = 31646541;
    List<Integer> decodedNumberList = processNumberService.decodeNumber(String.valueOf(smallInteger));
    assertTrue(decodedNumberList.size()==3);
    System.out.println("Small Integer Result: " + decodedNumberList);
  }

  @Test
  void testBigInteger() {
    String largeIntegerString = "2277114742311135167021343424004141432061264036716605455" +
            "35070012425145143366515462107042711115720106717127670062" +
            "71704657770433346073017047360217626325467150763006577133" +
            "54152655466766041402716542311111131505761476052650004524" +
            "21616177052165224543311447543654741617367042213645643631" +
            "33346575330621635642541636644326535501666004333326756424" +
            "47003252221104064117622317044717471111";
    List<Integer> decodedNumberList = processNumberService.decodeNumber(String.valueOf(largeIntegerString));
    assertTrue(decodedNumberList.size()==140);
    System.out.println("Big Integer Result: " + decodedNumberList);
  }
}