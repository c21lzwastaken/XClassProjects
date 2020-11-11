import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QuadraticTest {
    Quadratic q1 = new Quadratic(1,4,4);
    Quadratic q2 = new Quadratic(1,0,-4);
    Quadratic q3 = new Quadratic(1,2,-15);
    Quadratic q4 = new Quadratic(0,1,0);

    @Before

    @Test
    public void getA (){
        assertThat(q1.getA(), is(1.0));
    }
    @Test
    public void getB (){
        assertThat(q1.getB(), is(4.0));
    }
    @Test
    public void getC (){
        assertThat(q1.getC(), is(4.0));
    }

    @Test
    public void getValue1 (){
        assertThat(q1.getY(0), is(4.0));
    }
    @Test
    public void getValue2 (){
        assertThat(q1.getY(1), is(9.0));
    }
    @Test
    public void getValue3 (){
        assertThat(q1.getY(-2), is(0.0));
    }
    @Test
    public void getValue4 (){
        assertThat(q3.getY(2), is(-7.0));
    }
    /*
    @Test
    public void getValue5 (){
        assertThat(q4.getY(1), is(0.0));
    }
    */

    @Test
    public void getRoot1 (){
        assertThat(q1.getRoots(), is(new double[]{-2, -2}));
    }
    @Test
    public void getRoot2 (){
        assertThat(q2.getRoots(), is(new double[]{-2, 2}));
    }
    @Test
    public void getRoot3 (){
        assertThat(q3.getRoots(), is(new double[]{-5, 3}));
    }
    /*
    @Test
    public void getRoots4 (){
        assertThat(q4.getRoots(), is(new double[]{-0.0, -0.0}));
    }
    */

    @Test
    public void getAxis1 (){
        assertThat(q1.getAxis(), is(-2.0));
    }
    @Test
    public void getAxis2 (){
        assertThat(q2.getAxis(), is(-0.0));
    }
    @Test
    public void getAxis3 (){
        assertThat(q3.getAxis(), is(-1.0));
    }

    @Test
    public void getExtreme1 (){
        assertThat(q1.getExtreme(), is(0.0));
    }
    @Test
    public void getExtreme2 (){
        assertThat(q2.getExtreme(), is(-4.0));
    }
    @Test
    public void getExtreme3 (){
        assertThat(q3.getExtreme(), is(-16.0));
    }

    @Test
    public void isMax1 (){
        assertThat(q1.isMax(), is(false));
    }
    @Test
    public void isMax2 (){
        assertThat(q2.isMax(), is(false));
    }
    @Test
    public void isMax3 (){
        assertThat(q3.isMax(), is(false));
    }
}
