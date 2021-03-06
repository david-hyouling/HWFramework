package java.util.concurrent.atomic;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.Striped64;

public class DoubleAdder extends Striped64 implements Serializable {
    private static final long serialVersionUID = 7249069246863182397L;

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 7249069246863182397L;
        private final double value;

        SerializationProxy(DoubleAdder a) {
            this.value = a.sum();
        }

        private Object readResolve() {
            DoubleAdder a = new DoubleAdder();
            a.base = Double.doubleToRawLongBits(this.value);
            return a;
        }
    }

    public void add(double x) {
        Striped64.Cell[] cellArr = this.cells;
        Striped64.Cell[] as = cellArr;
        if (cellArr == null) {
            long b = this.base;
            if (casBase(b, Double.doubleToRawLongBits(Double.longBitsToDouble(b) + x))) {
                return;
            }
        }
        boolean uncontended = true;
        if (as != null) {
            int length = as.length - 1;
            int m = length;
            if (length >= 0) {
                Striped64.Cell cell = as[getProbe() & m];
                Striped64.Cell a = cell;
                if (cell != null) {
                    long v = a.value;
                    boolean cas = a.cas(v, Double.doubleToRawLongBits(Double.longBitsToDouble(v) + x));
                    uncontended = cas;
                    if (cas) {
                        return;
                    }
                }
            }
        }
        doubleAccumulate(x, null, uncontended);
    }

    public double sum() {
        Striped64.Cell[] as = this.cells;
        double sum = Double.longBitsToDouble(this.base);
        if (as != null) {
            for (Striped64.Cell a : as) {
                if (a != null) {
                    sum += Double.longBitsToDouble(a.value);
                }
            }
        }
        return sum;
    }

    public void reset() {
        Striped64.Cell[] as = this.cells;
        this.base = 0;
        if (as != null) {
            for (Striped64.Cell a : as) {
                if (a != null) {
                    a.reset();
                }
            }
        }
    }

    public double sumThenReset() {
        Striped64.Cell[] as = this.cells;
        double sum = Double.longBitsToDouble(this.base);
        this.base = 0;
        if (as != null) {
            for (Striped64.Cell a : as) {
                if (a != null) {
                    long v = a.value;
                    a.reset();
                    sum += Double.longBitsToDouble(v);
                }
            }
        }
        return sum;
    }

    public String toString() {
        return Double.toString(sum());
    }

    public double doubleValue() {
        return sum();
    }

    public long longValue() {
        return (long) sum();
    }

    public int intValue() {
        return (int) sum();
    }

    public float floatValue() {
        return (float) sum();
    }

    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }
}
