import java.util.*;


public class PetaKalselForm extends javax.swing.JFrame {

    public static List<PetaKalselForm.Path> ConnectingPath = new ArrayList<PetaKalselForm.Path>();
    public static List<PetaKalselForm.City> Loc = new ArrayList<PetaKalselForm.City>();
    public static final String ASTAR = "astar";
    public static final String GREEDY = "greedy";
    public static final String DYNAMIC = "dynamic";
    private static final String PATH_SEPERATOR = ",";
    
        public void intialize() {

        //Adding the location of the cities
        Loc.add(new City("anjir pasar",0,0));
        Loc.add(new City("marabahan",0,0));
        Loc.add(new City("banjarmasin",0,0));
        Loc.add(new City("pelaihari",0,0));
        Loc.add(new City("batakan",0,0));
        Loc.add(new City("banjarbaru",0,0));
        Loc.add(new City("martapura",0,0));
        Loc.add(new City("rantau",0,0));
        Loc.add(new City("kahelaan",0,0));
        Loc.add(new City("kandangan",0,0));
        Loc.add(new City("barabai",0,0));
        Loc.add(new City("amuntai",0,0));
        Loc.add(new City("paringin",0,0));
        Loc.add(new City("kintap",0,0));
        Loc.add(new City("batulicin",0,0));
        Loc.add(new City("hampang",0,0));
        Loc.add(new City("bakau",0,0));
        Loc.add(new City("kotabaru",0,0));
        Loc.add(new City("sungai bali",0,0));
        Loc.add(new City("lontar",0,0));
        

        // Adding the distance between connecting cities
                ConnectingPath.add(new Path("Bakau", "Hampang", 111));
		ConnectingPath.add(new Path("Hampang", "Barabai", 129));
		ConnectingPath.add(new Path("Barabai", "Paringin", 46));
		ConnectingPath.add(new Path("Barabai", "Amuntai", 21));
		ConnectingPath.add(new Path("Barabai", "Kandangan", 41));
		ConnectingPath.add(new Path("Kandangan", "Rantau", 34));
		ConnectingPath.add(new Path("Rantau", "Martapura", 74));
		ConnectingPath.add(new Path("Martapura", "Banjarbaru", 10));
		ConnectingPath.add(new Path("Banjarbaru", "Banjarmasin", 32));
		ConnectingPath.add(new Path("Banjarmasin", "Marabahan", 62));
		ConnectingPath.add(new Path("Marabahan", "Anjir Pasar", 70));
		ConnectingPath.add(new Path("Anjir Pasar", "Banjarmasin", 29));
		ConnectingPath.add(new Path("Banjarmasin", "Pelaihari", 91));
		ConnectingPath.add(new Path("Banjarbaru", "Pelaihari", 54)); 
		ConnectingPath.add(new Path("Pelaihari", "Batakan", 44));
		ConnectingPath.add(new Path("Pelaihari", "Kintap", 87));
		ConnectingPath.add(new Path("Kintap", "Batulicin", 124));
		ConnectingPath.add(new Path("Batulicin", "Hampang", 91));
		ConnectingPath.add(new Path("Batulicin", "Kotabaru", 56));
		ConnectingPath.add(new Path("Kotabaru", "Bakau", 214));  
		ConnectingPath.add(new Path("Kotabaru", "Sungai Bau", 20));
		ConnectingPath.add(new Path("Sungai Bau", "Lontar", 100)); 

    }


    // Class to Define getters and setters for city
    public class City {
        private String cityName;
        private double lat;
        private double lon;

        public City(String cityName, double lat, double lon) {
            super();
            this.cityName = cityName;
            this.lat = lat;
            this.lon = lon;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }

    //Class to define the getters and setters for path cost between connecting cities
    public class Path {
        private String sourceCity;
        private String destinationCity;
        private int cost;

        public Path(String sourceCity, String destinationCity, int cost) {
            super();
            this.sourceCity = sourceCity;
            this.destinationCity = destinationCity;
            this.cost = cost;
        }

        public String getSourceCity() {
            return sourceCity;
        }
        public void setSourceCity(String sourceCity) {
            this.sourceCity = sourceCity;
        }
        public String getDestinationCity() {
            return destinationCity;
        }
        public void setDestinationCity(String destinationCity) {
            this.destinationCity = destinationCity;
        }
        public int getCost() {
            return cost;
        }
        public void setCost(int cost) {
            this.cost = cost;
        }

        //TO check if source and destination is same
        @Override
        public boolean equals(Object o) {
            if(o != null && o instanceof Path) {
                Path r = (Path)o;
                if(r.getSourceCity().equals(sourceCity) && r.getDestinationCity().equals(destinationCity)) {
                    return true;
                } else if(r.getSourceCity().equals(destinationCity) && r.getDestinationCity().equals(sourceCity)) {
                    return true;
                }
            }
            return false;
        }
    }

    // Class to get and set the value of path, actual cost adn heuristic cost
    class Node implements Comparable<Node>{
        private String path;
        private int actualCost;
        private double hCost;

        public Node(String path, int actualCost, double hCost) {
            super();
            this.path = path;
            this.actualCost = actualCost;
            this.hCost = hCost;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getActualCost() {
            return actualCost;
        }

        public void setActualCost(int actualCost) {
            this.actualCost = actualCost;
        }

        public double getHeuristicCost() {
            return hCost;
        }

        public void setHeuristicCost(double hCost) {
            this.hCost = hCost;
        }

        // Function to compare the value of cost
        @Override
        public int compareTo(Node o) {
            double totalCost = actualCost + hCost;
            double totalCost_o = o.getActualCost() + o.getHeuristicCost();
            if(totalCost == totalCost_o) {
                return 0;
            } else if (totalCost > totalCost_o) {
                return 1;
            } else if (totalCost < totalCost_o) {
                return -1;
            }
            return 0;
        }

        // To check if the city is already in the frontier with a higher cost or if its a new city
        @Override
        public boolean equals(Object obj) {
            if(obj != null && obj instanceof Node) {
                Node newNode = (Node)obj;
                String existingCity = getCurrentCity(getPath());
                String newCity = getCurrentCity(newNode.getPath());
                if(existingCity.equalsIgnoreCase(newCity)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void logOutput(String msg) {
        System.out.println(msg);
    }
    
    public PetaKalselForm() {  
       initComponents();   
    }
    
    public void eksekusi(){
        PetaKalselForm search = new PetaKalselForm();
        this.setVisible(false);
        search.setVisible(true);
        
        search.intialize();
            String searchType = tipe.getSelectedItem().toString();
            String sourceCity = awal.getSelectedItem().toString();
            String destinationCity = tujuan.getSelectedItem().toString();
            

            if (null != searchType) switch (searchType) {
            case ASTAR:
                
                search.astar(sourceCity, destinationCity);  
                
                break;
            case GREEDY:
                
                search.greedy(sourceCity, destinationCity);
                
                break;
            case DYNAMIC:
                search.dynamic(sourceCity, destinationCity);
                
                break;
            default:
                break;
        }
    }
    private void astar(String sourceCity, String destinationCity) {
        Queue<Node> queue = new PriorityQueue<Node>();
        List<String> visitedList = new ArrayList<String>();
        queue.add(new Node(sourceCity, 0, 0));
        int totalNodesExpanded = 0;
        while(!queue.isEmpty()) {
            // Check if the queue is empty
            Node currentNode = queue.remove();
            // Removing the current node from the queue
            if(goalChecker(currentNode.getPath(), destinationCity)) {
                // To check if the goal is achieved
               jTextField2.setText(String.valueOf(totalNodesExpanded));
                jTextField3.setText(String.valueOf(currentNode.getPath()));
                jTextField4.setText(String.valueOf((currentNode.getPath())));
                jTextField5.setText(String.valueOf(currentNode.actualCost));
                return;
            } else {
                String currentCity = getCurrentCity(currentNode.getPath());
                // Get the expanded list of cities from the current source
                if(totalNodesExpanded != 0) {
                    System.out.print("");
                } else {
                    System.out.print("");
                }
                jTextArea1.append(currentCity+", ");
                totalNodesExpanded ++;
                if(!visitedList.contains(currentCity)) {
                    // To check if the city has been visited
                    visitedList.add(currentCity);
                }
                List<Node> possibleMoves = moveGenerator(currentCity);
                // Defining the move generator
                for (Node possibleMove : possibleMoves) {
                    if(!visitedList.contains(possibleMove.getPath())) {
                        // Calculate the heuristic value to add the city in the queue
                        String newPath = currentNode.getPath() + PATH_SEPERATOR + possibleMove.getPath();
                        int totalCost = currentNode.getActualCost() + possibleMove.getActualCost();
                        double newHeuristicCost = calcHeuristicCost(possibleMove.getPath(), destinationCity);
                        Node node = new Node(newPath, totalCost, newHeuristicCost);
                        if(queue.contains(node)) {
                            updateQueueNode(queue, node);
                        } else {
                            queue.add(node);
                        }
                    }
                }
            }
        }

        logOutput("\nNo path found for A*");
    }

    // Function of Dynamic
    private void dynamic(String sourceCity, String destinationCity) {
        Queue<Node> queue = new PriorityQueue<Node>();
        List<String> visitedList = new ArrayList<String>();
        queue.add(new Node(sourceCity, 0, 0));
        int totalNodesExpanded = 0;
        while(!queue.isEmpty()) {
            // Check if the queue is empty
            Node currentNode = queue.remove();
            // Removing the current node from the queue
            if(goalChecker(currentNode.getPath(), destinationCity)) {
                // To check if the goal is achieved
                
                jTextField2.setText(String.valueOf(totalNodesExpanded));
                jTextField3.setText(String.valueOf(currentNode.getPath()));
                jTextField4.setText(String.valueOf((currentNode.getPath())));
                jTextField5.setText(String.valueOf(currentNode.actualCost));
                return;
            } else {
                String currentCity = getCurrentCity(currentNode.getPath());
                // Get the expanded list of cities from the current source
                if(totalNodesExpanded != 0) {
                    System.out.print("");
                } else {
                    System.out.print("");
                }
                totalNodesExpanded++;
                jTextArea1.append(currentCity+", ");
                visitedList.add(currentCity);
                List<Node> possibleMoves = moveGenerator(currentCity);
                // Defining the move generator
                for (Node possibleMove : possibleMoves) {
                    if(!visitedList.contains(possibleMove.getPath())) {
                        // Calculate the path value from the source to add the city in the queue
                        String newPath = currentNode.getPath() + PATH_SEPERATOR + possibleMove.getPath();
                        int totalCost = currentNode.getActualCost() + possibleMove.getActualCost();
                        Node node = new Node(newPath, totalCost, 0);
                        if(queue.contains(node)) {
                            updateQueueNode(queue, node);
                        } else {
                            queue.add(node);
                        }
                    }
                }
            }
        }
        logOutput("\nNo path found for dynamic Programming");
    }

    private void greedy(String sourceCity, String destinationCity) {
        Queue<Node> queue = new PriorityQueue<Node>();
        List<String> visitedList = new ArrayList<String>();
        queue.add(new Node(sourceCity, 0, 0));
        int totalNodesExpanded = 0;
        while(!queue.isEmpty()) {
            // Check if the queue is empty
            Node currentNode = queue.remove();
            // Removing the current node from the queue
            if(goalChecker(currentNode.getPath(), destinationCity)) {
                // To check if the goal is achieved
                
                jTextField2.setText(String.valueOf(totalNodesExpanded));
                jTextField3.setText(String.valueOf(currentNode.getPath()));
                jTextField4.setText(String.valueOf(getPathLength(currentNode.getPath())));
                int totalCost = getPathCost(currentNode.getPath());
                jTextField5.setText(String.valueOf(totalCost));
                return;
            } else {
                String currentCity = getCurrentCity(currentNode.getPath());
                // Get the expanded list of cities from the current source
                if(totalNodesExpanded != 0) {
                    System.out.print("");
                } else {
                    System.out.print("");
                }
                totalNodesExpanded++;
                jTextArea1.append(currentCity+", ");
                visitedList.add(currentCity);
                List<Node> possibleMoves = moveGenerator(currentCity);
                // Defining the move generator
                for (Node possibleMove : possibleMoves) {
                    if(!visitedList.contains(possibleMove.getPath())) {
                        // Calculate the heuristic value to add the city in the queue
                        String newPath = currentNode.getPath() + PATH_SEPERATOR + possibleMove.getPath();
                        double newHeuristicCost = calcHeuristicCost(possibleMove.getPath(), destinationCity);
                        Node node = new Node(newPath, 0, newHeuristicCost);
                        if(queue.contains(node)) {
                            updateQueueNode(queue, node);
                        } else {
                            queue.add(node);
                        }
                    }
                }
            }
        }
        logOutput("\nNo path found for Greedy");
    }

    private int getPathLength(String path) {
        // Get the path length
        String[] cities = path.split(PATH_SEPERATOR);
        return cities.length;
    }

    private int getPathCost(String path) {
        // Calculate teh total path cost
        String[] cities = path.split(PATH_SEPERATOR);
        int totalCost = 0;
        for (int i = 0; i < cities.length - 1 ; i++) {
            Path r = new Path(cities[i], cities[i+1], 0);
            if(ConnectingPath.contains(r)) {
                int indexOf = ConnectingPath.indexOf(r);
                Path road = ConnectingPath.get(indexOf);
                totalCost += road.getCost();
            }
        }
        return totalCost;
    }

    private static boolean goalChecker(String path, String destinationCityName) {
        // To check if the goal or destination has been reached
        String lastVisitedCity = getCurrentCity(path);
        if(lastVisitedCity != null && !lastVisitedCity.trim().equals("") && lastVisitedCity.equalsIgnoreCase(destinationCityName)) {
            return true;
        }
        return false;
    }

    private List<Node> moveGenerator(String currentCity) {
        // Defining the move generator with all the neighbours
        List<Node> possibleMoves = new ArrayList<Node>();
        if(currentCity != null) {
            for (Path road : ConnectingPath) {
                if(road.getSourceCity().equalsIgnoreCase(currentCity)) {
                    possibleMoves.add(new Node(road.getDestinationCity(), road.getCost(), 0));
                } else if (road.getDestinationCity().equalsIgnoreCase(currentCity)) {
                    possibleMoves.add(new Node(road.getSourceCity(), road.getCost(), 0));
                }
            }
        }
        return possibleMoves;
    }

    private static String getCurrentCity(String currentPath) {
        // To get the value of the last visited city or the current city
        if(currentPath != null) {
            int index = currentPath.lastIndexOf(PATH_SEPERATOR);
            if(index != -1)
            {
                String lastVisitedCity = currentPath.substring(index + 1);
                return lastVisitedCity;
            } else {
                return currentPath;
            }
        }
        return null;
    }

    private void updateQueueNode(Queue<Node> queue, Node newNode) {
        // update the queue node when a lower cost path is discovered
        for (Node existingNode : queue) {
            if(existingNode.equals(newNode)) {
                if(existingNode.getActualCost() + existingNode.getHeuristicCost() > newNode.getActualCost() + newNode.getHeuristicCost()) {
                    existingNode.setPath(newNode.getPath());
                    existingNode.setActualCost(newNode.getActualCost());
                    existingNode.setHeuristicCost(newNode.getHeuristicCost());
                }
            }
        }
    }

    private double calcHeuristicCost(String path, String destinationCity) {
        // Passing values to calculate the heuristic cost of path
        double lat1 = 0;
        double lat2 = 0;
        double lon1 = 0;
        double lon2 = 0;
        for (City city : Loc) {
            if(city.getCityName().equalsIgnoreCase(path)) {
                lat1 = city.getLat();
                lon1 = city.getLon();
            }
            if(city.getCityName().equalsIgnoreCase(destinationCity)) {
                lat2 = city.getLat();
                lon2 = city.getLon();
            }
        }
        double hCost = heurisiticFunc(lat1,lon1, lat2, lon2);
        return hCost;
    }

    private double heurisiticFunc(double lat1, double lon1,
                                     double lat2, double lon2) {
        // Calculation of heuristic cost using the formula
        double lat_sum = lat1 + lat2;
        double lat_sub = lat1 - lat2;
        double long_sub = lon1 - lon2;
        double term1 = Math.pow(69.5 * lat_sub, 2);
        double term2 = Math.pow(69.5 * Math.cos(lat_sum / 360 * Math.PI) * long_sub, 2);
        double hVal = Math.sqrt(term1 + term2);
        return hVal;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipe = new javax.swing.JComboBox<>();
        awal = new javax.swing.JComboBox<>();
        tujuan = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PENCARIAN");

        tipe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "greedy", "astar", "dynamic" }));

        awal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Anjir Pasar", "Marabahan", "Banjarmasin", "Pelaihari", "Batakan", "Banjarbaru", "Martapura", "Rantau", "Kahelaan", "Kandangan", "Barabai", "Amuntai", "Paringin", "Kintap", "Batulicin", "Hampang", "Bakau", "Kotabaru", "Sungai Bali", "Lontar" }));

        tujuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Anjir Pasar", "Marabahan", "Banjarmasin", "Pelaihari", "Batakan", "Banjarbaru", "Martapura", "Rantau", "Kahelaan", "Kandangan", "Barabai", "Amuntai", "Paringin", "Kintap", "Batulicin", "Hampang", "Bakau", "Kotabaru", "Sungai Bali", "Lontar" }));

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipe Algoritma :");

        jLabel2.setText("Awal Kota :");

        jLabel3.setText("Tujuan Kota :");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel4.setText("Nodes Expanded Are : ");

        jLabel5.setText("Total nodes Expanded : ");

        jLabel6.setText("Jalurnya :");

        jLabel7.setText("Path length generated :");

        jLabel8.setText("Cost : ");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PETA.png"))); // NOI18N
        jLabel9.setText("jLabel9");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipe, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(awal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jButton1))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel9)
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(awal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(223, 223, 223))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        eksekusi();     
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PetaKalselForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PetaKalselForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PetaKalselForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PetaKalselForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PetaKalselForm().setVisible(true);        
           }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> awal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField3;
    public javax.swing.JTextField jTextField4;
    public javax.swing.JTextField jTextField5;
    private javax.swing.JComboBox<String> tipe;
    private javax.swing.JComboBox<String> tujuan;
    // End of variables declaration//GEN-END:variables
}
