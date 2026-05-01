package HOA8;

public class imh
{
    /*
    CONCEPT
    Understand that number of internai nodes is size / 2 by integer division.
    And realize that array is organized in level order.
    So, by definition, minimum heap is disrupted when array[i] > array[2 * i] or array[i] > array[2 * i + 1].
    Caution about special cases such as deficiency of 1 final right node.
    */

    public static boolean isMinimumHeap(int[] pq, int size)
    {
        if(size % 2 == 0)
        {
            for(int i = 1; i < size / 2; i++)
            {
                if(pq[i] > pq[2 * i] || pq[i] > pq[2 * i + 1]) return false;
            }
        }
        else
        {
            for(int i = 1; i < size / 2; i++)
            {
                if(pq[i] > pq[2 * i] || pq[i] > pq[2 * i + 1]) return false;
            }

            if(pq[size / 2] > pq[size / 2 * 2]) return false;
        }

        return true;
    }
}
