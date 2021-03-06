package cgeo.geocaching.sorting;

import cgeo.geocaching.Geocache;

import android.test.AndroidTestCase;

public class NameComparatorTest extends AndroidTestCase {

    private static class NamedCache extends Geocache {

        public NamedCache(final String name) {
            this.setName(name);
        }
    }

    private NameComparator comp = new NameComparator();

    public void testLexical() {
        assertSorted(new NamedCache("A"), new NamedCache("Z"));
        assertNotSorted(new NamedCache("Z"), new NamedCache("A"));
    }

    public void testNumericalNamePart() {
        assertSorted(new NamedCache("AHR#2"), new NamedCache("AHR#11"));
        assertSorted(new NamedCache("AHR#7 LP"), new NamedCache("AHR#11 Bonsaibuche"));
        assertSorted(new NamedCache("2"), new NamedCache("11"));
    }

    public void testNumericalWithSuffix() {
        assertSorted(new NamedCache("abc123def"), new NamedCache("abc123xyz"));
        assertEquals("abc000123def456", (new NamedCache("abc123def456")).getNameForSorting());
    }

    private void assertSorted(final Geocache cache1, final Geocache cache2) {
        assertTrue(comp.compare(cache1, cache2) < 0);
    }

    private void assertNotSorted(final Geocache cache1, final Geocache cache2) {
        assertTrue(comp.compare(cache1, cache2) > 0);
    }
}
