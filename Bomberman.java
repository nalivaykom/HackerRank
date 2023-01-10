public static List<String> bomberMan(int n, List<String> grid) {
    // Write your code here
        
        if (n == 1) {
            return grid;
        } else if (n % 2 == 0) {
            return zeroGrid(grid);
        }
        List<String> prevGridCopy = new ArrayList<String>(grid);
        
        long timeCount = 2;
        grid = zeroGrid(grid);
        timeCount++;
        Map<List<String>, Long> repeatList = new HashMap<List<String>, Long>();
        long caughtNumberStart = -1;
        while (timeCount <= n) {
           
            if (timeCount % 2 == 1) {
                grid = blowGrid(prevGridCopy, grid);
                if (repeatList.get(grid) != null) {
                    caughtNumberStart = repeatList.get(grid);
                    repeatList.put(new ArrayList<String>(grid), timeCount);
                    break;
                }
                repeatList.put(new ArrayList<String>(grid), timeCount);
                timeCount++;
            } else {
                prevGridCopy = new ArrayList<String>(grid);
                grid = zeroGrid(grid);
                timeCount++;
            }
        }
        
        if (caughtNumberStart == -1) {
            return grid;
        }

        long loopLength = timeCount - caughtNumberStart;
        long newN = ((n) % (loopLength)) + loopLength;

        List<String> returnGrid = new ArrayList<String>();
        for (List<String> key : repeatList.keySet()) {
            if (repeatList.get(key) == newN) {
                returnGrid = new ArrayList<String>(key);
            }
        }
        return returnGrid;
    }
    
    public static List<String> blowGrid(List<String> prev, List<String> grid) {
        
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).length(); j++) {
                if (prev.get(i).charAt(j) == 'O') {
                    grid = blow(i, j, grid);
                }
            }
        }
        
        return grid;
    }
    
    public static List<String> blow(int i, int j, List<String> grid) {
        int iHigh = grid.size() - 1;
        int jHigh = grid.get(0).length() - 1;
        
        if (i - 1 > -1) {
            String s = grid.get(i - 1);
            s = s.substring(0, j) + '.' + s.substring(j+1, s.length());
            grid.set(i - 1, s);
        }
        if (i + 1 <= iHigh) {
            String s = grid.get(i + 1);
            s = s.substring(0, j) + '.' + s.substring(j+1, s.length());
            grid.set(i + 1, s);
        }
        if ((j - 1 > -1) && (j + 1 <= jHigh)) {
            String s = grid.get(i);
            s = s.substring(0, j - 1) + "..." + s.substring(j + 2, s.length());
            grid.set(i, s);
        } else {
            if (j - 1 > -1) {
                String s = grid.get(i);
                s = s.substring(0, j - 1) + ".." + s.substring(j + 1, s.length());
                grid.set(i, s);
            } 
            if (j + 1 <= jHigh) {
                String s = grid.get(i);
                s = s.substring(0, j) + ".." + s.substring(j + 2, s.length());
                grid.set(i, s);
            }
            
        }
        String s = grid.get(i);
        s = s.substring(0, j) + "." + s.substring(j + 1, s.length());
        grid.set(i, s);
        return grid;
    }
    
    public static List<String> zeroGrid(List<String> grid) {
        String s = "";
        for (int j = 0; j < grid.get(0).length(); j++) {
                s = s + "O";
        }
        for (int i = 0; i < grid.size(); i++) {
            grid.set(i, s);
        }
        return grid;   
    }
