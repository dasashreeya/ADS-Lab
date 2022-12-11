import java.util.ArrayList;
 
class GFG{
 
static class Edge
{
    int first, second;
     
    Edge(int x, int y)
    {
        this.first = x;
        this.second = y;
    }
}
 
// Function that implements the Find
static int Find(int[] hashSet, int val)
{
     
    // Get the val
    int parent = val;
 
    // Until parent is not found
    while (parent != hashSet[parent])
    {
        parent = hashSet[parent];
    }
     
    // Return the parent
    return parent;
}
 
// Function that implements the Union
static void Union(int[] hashSet, int first, int second)
{
     
    // Find the first father
    int first_father = Find(hashSet, first);
 
    // Find the second father
    int second_father = Find(hashSet, second);
 
    // If both are unequals then update
    // first father as ssecond_father
    if (first_father != second_father)
        hashSet[first_father] = second_father;
}
 
// Recursive Function that change all
// the corners connected 1s to 0s
static void change(char[][] matrix, int x, int y,
                                    int n, int m)
{
     
    // If already zero then return
    if (x < 0 || y < 0 || x > m - 1 ||
        y > n - 1 || matrix[x][y] == '0')
        return;
 
    // Change the current cell to '0'
    matrix[x][y] = '0';
 
    // Recursive Call for all the
    // four corners
    change(matrix, x + 1, y, n, m);
    change(matrix, x, y + 1, n, m);
    change(matrix, x - 1, y, n, m);
    change(matrix, x, y - 1, n, m);
}
 
// Function that changes all the
// connected 1s to 0s at the corners
static void changeCorner(char[][] matrix)
{
     
    // Dimensions of matrix
    int m = matrix.length;
    int n = matrix[0].length;
 
    // Traverse the matrix
    for(int i = 0; i < m; i++)
    {
        for(int j = 0; j < n; j++)
        {
             
            // If corner cell
            if (i * j == 0 || i == m - 1 || j == n - 1)
            {
                 
                // If value is 1s, then
                // recursively change to 0
                if (matrix[i][j] == '1')
                {
                    change(matrix, i, j, n, m);
                }
            }
        }
    }
}
 
// Function that counts the number
// of island in the given matrix
static int numIslands(char[][] matrix)
{
    if (matrix.length == 0)
        return 0;
 
    // Dimensions of the matrix
    int m = matrix.length;
    int n = matrix[0].length;
 
    // Make all the corners connecting
    // 1s to zero
    changeCorner(matrix);
 
    // First convert to 1 dimension
    // position and convert all the
    // connections to edges
    ArrayList<Edge> edges = new ArrayList<Edge>();
 
    for(int i = 0; i < m; i++)
    {
        for(int j = 0; j < n; j++)
        {
             
            // If the cell value is 1
            if (matrix[i][j] == '1')
            {
                int id = i * n + j;
 
                // Move right
                if (j + 1 < n)
                {
                     
                    // If right cell is
                    // 1 then make it as
                    // an edge
                    if (matrix[i][j + 1] == '1')
                    {
                        int right = i * n + j + 1;
 
                        // Push in edge vector
                        edges.add(new Edge(id, right));
                    }
                }
                 
                // Move down
                if (i + 1 < m)
                {
                     
                    // If right cell is
                    // 1 then make it as
                    // an edge
                    if (matrix[i + 1][j] == '1')
                    {
                        int down = (i + 1) * n + j;
 
                        // Push in edge vector
                        edges.add(new Edge(id, down));
                    }
                }
            }
        }
    }
 
    // Construct the Union Find structure
    int[] hashSet = new int[m * n];
    for(int i = 0; i < m * n; i++)
    {
        hashSet[i] = i;
    }
 
    // Next apply Union Find for all
    // the edges stored
    for(Edge edge : edges)
    {
        Union(hashSet, edge.first, edge.second);
    }
 
    // To count the number of connected
    // islands
    int numComponents = 0;
 
    // Traverse to find the islands
    for(int i = 0; i < m * n; i++)
    {
        if (matrix[i / n][i % n] == '1' &&
            hashSet[i] == i)
            numComponents++;
    }
 
    // Return the count of the island
    return numComponents;
}
 
// Driver Code
public static void main(String[] args)
{
     
    // Given size of Matrix
    int N = 5, M = 8;
 
    // Given Matrix
    char[][] matrix = { { '0', '0', '0', '0',
                          '0', '0', '0', '1' },
                        { '0', '1', '1', '1',
                          '1', '0', '0', '1' },
                        { '0', '1', '0', '1',
                          '0', '0', '0', '1' },
                        { '0', '1', '1', '1',
                          '1', '0', '1', '0' },
                        { '0', '0', '0', '0',
                          '0', '0', '0', '1' } };
 
    // Function Call
    System.out.println(numIslands(matrix));
}
}
