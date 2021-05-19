package org.javainaction.graph;

import java.util.function.*;
import java.util.*;

class RectangleMania {
    static enum DIRECTION {
        UP("up"),
        DOWN("down"),
        LEFT("left"),
        RIGHT("right");

        private final String direction;

        DIRECTION(String direction){
            this.direction = direction;
        }
    }

    public static void main(String[] args) {
        Point[] coords = new Point[]
                {new Point(0, 0), new Point(0, 1),  new Point(1, 1),  new Point(1, 0)};
                       // ,  new Point(2, 1),  new Point(2, 0),  new Point(3, 1),  new Point(3, 0)};
        System.out.println(rectangleMania(coords));
    }

    //O(n^2) time | O(n^2) space where n is numnber of coordinates
    public static int rectangleMania(Point[] coords) {
        Map<String, Map<DIRECTION, List<Point>>> coordsTable =
                getCoordsTable(coords);
        return getRectangleCount(coords, coordsTable);
    }

    public static int getRectangleCount(Point[] coords,
                                        Map<String, Map<DIRECTION, List<Point>>> coordsTable) {
        int rectCount = 0;
        for (Point coord : coords) {
            rectCount += clockWiseCountRectangles(coord, coordsTable, DIRECTION.UP, coord);
        }
        return rectCount;
    }

    public static int clockWiseCountRectangles(Point coord,
                                               Map<String, Map<DIRECTION, List<Point>>> coordsTable,
                                               DIRECTION direction,
                                               Point origin) {
        String coordString = Integer.toString(coord.x) + "-" +
                Integer.toString(coord.y);
        if (direction == DIRECTION.LEFT) {
            boolean rectangleFound = coordsTable
                    .get(coordString)
                    .get(DIRECTION.LEFT)
                    .contains(origin);
            return rectangleFound ? 1 : 0;
        } else {
            int rectCount = 0;
            DIRECTION nextDirection = getNextClockwiseDirection(direction);
            for (Point nextCoord : coordsTable.get(coordString).get(direction)) {
                rectCount += clockWiseCountRectangles(nextCoord, coordsTable, nextDirection, origin);
            }
            return rectCount;
        }
    }

    public static DIRECTION getNextClockwiseDirection(DIRECTION direction) {
        switch(direction) {
            case UP : return DIRECTION.RIGHT;
            case RIGHT : return DIRECTION.DOWN;
            case DOWN : return DIRECTION.LEFT;
        }
        return null;
    }

    public static Map<String, Map<DIRECTION, List<Point>>>
    getCoordsTable(Point[] coords) {
        Map<String, Map<DIRECTION, List<Point>>> coordsTable = new HashMap<>();
        for (Point coordOne : coords) {
            Map<DIRECTION, List<Point>> coordDirection = getEmptyCoordDirection();
            for (Point coordTwo : coords) {
                DIRECTION direction = directionFunc.apply(coordOne, coordTwo);
                if (coordDirection.containsKey(direction))
                    coordDirection.get(direction).add(coordTwo);
            }
            String coordString = Integer.toString(coordOne.x) + "-" +
                    Integer.toString(coordOne.y);
            coordsTable.put(coordString, coordDirection);
        }
        return coordsTable;
    }

    public static Map<DIRECTION, List<Point>> getEmptyCoordDirection() {
        Map<DIRECTION, List<Point>> coordDirection = new HashMap<>();
        coordDirection.put(DIRECTION.UP, new ArrayList<>());
        coordDirection.put(DIRECTION.DOWN, new ArrayList<>());
        coordDirection.put(DIRECTION.LEFT, new ArrayList<>());
        coordDirection.put(DIRECTION.RIGHT, new ArrayList<>());
        return coordDirection;
    }

    static BiFunction<Point, Point, DIRECTION> directionFunc = (one, two) -> {
        if (two.y == one.y) {
            if (two.x > one.x) return DIRECTION.RIGHT;
            else if (two.x < one.x) return DIRECTION.LEFT;
        } else if (two.x == one.x) {
            if (two.y > one.y) return DIRECTION.UP;
            else if (two.y < one.y) return DIRECTION.DOWN;
        }
        return null;
    };

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


