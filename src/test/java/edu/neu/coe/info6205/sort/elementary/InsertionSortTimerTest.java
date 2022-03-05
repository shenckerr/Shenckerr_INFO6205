package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.*;
import edu.neu.coe.info6205.util.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InsertionSortTimerTest {
    final static LazyLogger logger = new LazyLogger(InsertionSort.class);


    //////////////////////////////////////Ordered Array
    @Test
    public void testRepeat1() {
        final Timer timer = new Timer();

        final double mean = timer.repeat(100, () -> {
            final Random random = new Random();
            int n = random.nextInt(10);
            sortHelper(n+1);
            System.out.println(n);

            return null;
        });
        System.out.println(mean);

        assertEquals(100, new PrivateMethodTester(timer).invokePrivate("getLaps"));
        assertEquals(5, mean, 5);

    }


    //////////////////////////////////////Reverse Ordered Array
    @Test
    public void testRepeat2() {
        final Timer timer = new Timer();

        final double mean = timer.repeat(100, () -> {
            final Random random = new Random();
            int n = random.nextInt(10);
            sortHelper2(n+1);
            return null;
        });
        System.out.println(mean);
        assertEquals(100, new PrivateMethodTester(timer).invokePrivate("getLaps"));
        assertEquals(5, mean, 5);

    }




    //////////////////////////////////////Random Ordered Array
    @Test
    public void testRepeat3() {
        final Timer timer = new Timer();

        final double mean = timer.repeat(100, () -> {
            final Random random = new Random();
            int n = random.nextInt(10);
            sortHelper3(n+1);
            return null;
        });
        System.out.println(mean);
        assertEquals(100, new PrivateMethodTester(timer).invokePrivate("getLaps"));
        assertEquals(5, mean, 5);

    }


    //////////////////////////////////////Partially Ordered Array
    @Test
    public void testRepeat4() {
        final Timer timer = new Timer();

        final double mean = timer.repeat(100, () -> {

            sortHelper4();
            return null;
        });
        System.out.println(mean);
        assertEquals(100, new PrivateMethodTester(timer).invokePrivate("getLaps"));
        assertEquals(5, mean, 5);

    }



    //////////////////////////////////////Instrumenting set to false
    @Test
    public void testRepeat5() {
        final Timer timer = new Timer();

        final double mean = timer.repeat(100, () -> {

            sortHelper5();
            return null;
        });
        System.out.println(mean);
        assertEquals(100, new PrivateMethodTester(timer).invokePrivate("getLaps"));
        assertEquals(5, mean, 5);

    }



    //////////////////////////////////////Doubling the no. of elements each time
    @Test
    public void testRepeat6() {
        final Timer timer = new Timer();

        final double mean = timer.repeat(100, () -> {

            sortHelper6();
            return null;
        });
        System.out.println(mean);
        assertEquals(100, new PrivateMethodTester(timer).invokePrivate("getLaps"));
        assertEquals(5, mean, 5);

    }


    //////////////////////////////////////////////////
    private void sortHelper(int count){
        final List<Integer> list = new ArrayList<>();
        for(int i =0;i<count;i++){
            list.add(i+1);
        }
        Integer[] xs = list.toArray(new Integer[0]);
        final Config config = ConfigTest.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", list.size(), config);
        helper.init(list.size());
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.preProcess(xs);
        Integer[] ys = sorter.sort(xs);
        sorter.postProcess(ys);
    }

/////////////////////////////////////////////////
    private void sortHelper2(int count){
        final List<Integer> list = new ArrayList<>();
        for(int i =0;i<count;i++){
            list.add(count-i+1);
        }
        Integer[] xs = list.toArray(new Integer[0]);
        final Config config = ConfigTest.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", list.size(), config);
        helper.init(list.size());
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.preProcess(xs);
        Integer[] ys = sorter.sort(xs);
        sorter.postProcess(ys);

    }
    /////////////////////////////////////////////////////////////

    private void sortHelper3(int count){
        final List<Integer> list = new ArrayList<>();
        for(int i =0;i<count;i++){
            final Random random = new Random();
            int n = random.nextInt(count);
            list.add(n);
        }
        Integer[] xs = list.toArray(new Integer[0]);
        final Config config = ConfigTest.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", list.size(), config);
        helper.init(list.size());
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.preProcess(xs);
        Integer[] ys = sorter.sort(xs);
        sorter.postProcess(ys);

    }
/////////////////////////////////////////
    private void sortHelper4(){
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(3);
        Integer[] xs = list.toArray(new Integer[0]);
        final Config config = ConfigTest.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", list.size(), config);
        helper.init(list.size());
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.preProcess(xs);
        Integer[] ys = sorter.sort(xs);
        sorter.postProcess(ys);
    }

/////////////////////////////////////////////
    private void sortHelper5(){
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(3);
        Integer[] xs = list.toArray(new Integer[0]);
        final Config config = ConfigTest.setupConfig("false", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", list.size(), config);
        helper.init(list.size());

        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.preProcess(xs);
        Integer[] ys = sorter.sort(xs);
        sorter.postProcess(ys);
    }




//////////////////////////////////////////////////////
    private void sortHelper6(){
        final List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(4);
        list.add(7);
        list.add(3);
        list.add(9);
        list.add(8);
        list.add(1);
        list.add(2);

        list.add(15);
        list.add(42);
        list.add(71);
        list.add(13);
        list.add(92);
        list.add(84);
        list.add(13);
        list.add(27);

        list.add(65);
        list.add(48);
        list.add(75);
        list.add(31);
        list.add(95);
        list.add(89);
        list.add(109);
        list.add(243);

        list.add(115);
        list.add(420);
        list.add(711);
        list.add(113);
        list.add(192);
        list.add(844);
        list.add(183);
        list.add(270);
        Integer[] xs = list.toArray(new Integer[0]);
        final Config config = ConfigTest.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", list.size(), config);
        helper.init(list.size());
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(helper);
        final StatPack statPack = (StatPack) privateMethodTester.invokePrivate("getStatPack");
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.preProcess(xs);
        Integer[] ys = sorter.sort(xs);
        sorter.postProcess(ys);
    }
}
