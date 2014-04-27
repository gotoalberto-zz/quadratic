/*
 * Telefónica Digital - Product Development and Innovation
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Copyright (c) Telefónica Investigación y Desarrollo S.A.U.
 * All rights reserved.
 */

package quadratic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class QuadraticEquationWindow {

    private final Shell shell;
    private final Text inputA;
    private final Text inputB;
    private final Text inputC;
    private final Button solveButton;

    public QuadraticEquationWindow(Display display) {
        this.shell = new Shell(display);
        shell.setLayout(new RowLayout());

        inputA = coefficientInputText();
        inlineLabel("* x^2 +");
        inputB = coefficientInputText();
        inlineLabel("* x + ");
        inputC = coefficientInputText();
        solveButton = solveButton();
        shell.pack();
    }

    public void open() {
        this.shell.open();
    }

    public boolean isClosed() {
        return this.shell.isDisposed();
    }

    private Text coefficientInputText() {
        Text text = new Text(this.shell, SWT.BORDER);
        text.setText("100");
        text.pack();
        return text;
    }

    private void inlineLabel(String text) {
        Label label = new Label(this.shell, SWT.NONE);
        label.setText(text);
        label.pack();
    }

    private Button solveButton() {
        Button button = new Button(shell, SWT.PUSH);
        button.setText("Solve");
        button.addSelectionListener(new ButtonListener());
        button.pack();
        return button;
    }

    private static void errorDialog(Shell shell, String message) {
        MessageBox dialog = new MessageBox(shell, SWT.ERROR | SWT.OK);
        dialog.setText("Error");
        dialog.setMessage(message);
        dialog.open();
    }

    private class ButtonListener implements SelectionListener {
        @Override
        public void widgetSelected(SelectionEvent e) {
            try {
                double a = Double.valueOf(inputA.getText());
                double b = Double.valueOf(inputB.getText());
                double c = Double.valueOf(inputC.getText());

                if (a != 0) {
                    MessageBox dialog = new MessageBox(shell, SWT.OK);
                    dialog.setText("Result");
                    dialog.setMessage(formatSolutions(QuadraticEquation.solve(a, b, c)));
                    dialog.open();
                } else {
                    errorDialog(shell, "a should be non-zero but is " + a);
                }
            } catch (NumberFormatException ex) {
                errorDialog(shell, "Invalid number: " + ex.getMessage());
            }
        }

        @Override
        public void widgetDefaultSelected(SelectionEvent e) {}

        private String formatSolutions(double[] solutions) {
            switch(solutions.length) {
                case 0:
                    return "There are no solutions";
                case 1:
                    return "There is an unique solution: " + solutions[0];
                default:
                    return "There are two solutions: " + solutions[0] + ", " + solutions[1];
            }
        }

    }
}
