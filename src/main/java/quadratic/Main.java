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

import org.eclipse.swt.widgets.*;

public class Main {

    public static void main(String[] args) {
        final Display display = new Display();
        eventLoop(display, new QuadraticEquationWindow(display));
        display.dispose();
    }

    private static void eventLoop(Display display, QuadraticEquationWindow window) {
        window.open();
        while(!window.isClosed()) {
           if (!display.readAndDispatch()) {
               display.sleep();
           }
        }
    }
}
