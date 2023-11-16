package edu.project3.tableRenderers;

import java.util.List;

public class AdocTableRenderer implements TableRenderer {

    private static String getTableHeaderLine(Object[] row) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < row.length; i++) {
            String value = row[i].toString();
            stringBuilder.append("| ");
            stringBuilder.append(value);
            if (i != row.length - 1) {
                stringBuilder.append(" ");
            }
        }
        stringBuilder.append("\n");
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    private static String getTableLine(Object[] row) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < row.length; i++) {
            String value = row[i].toString();
            stringBuilder.append("| ");
            stringBuilder.append(value);
            stringBuilder.append("\n");
        }
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    public String render(String header, int tableWidth, List<Object[]> table) {
        StringBuilder stringBuilder = new StringBuilder("=== " + header + "\n\n");
        stringBuilder.append("[cols=\"1");
        for (int i = 1; i < tableWidth; i++) {
            stringBuilder.append(", 1");
        }
        stringBuilder.append("\"]\n");
        stringBuilder.append("|===\n");

        stringBuilder.append(getTableHeaderLine(table.get(0)));
        for (int row = 1; row < table.size(); row++) {
            stringBuilder.append(getTableLine(table.get(row)));
        }

        stringBuilder.append("|===\n\n");
        return stringBuilder.toString();
    }

}
