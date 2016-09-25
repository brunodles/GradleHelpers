package com.github.brunodles.cexec

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


class Cexec extends DefaultTask {

    private String command
    private boolean printCommand = true

    String getCommand() { return command }
    void setCommand(String command) { this.command = command }

    boolean getPrintCommand() { return printCommand }
    void setPrintCommand(boolean printCommand) { this.printCommand = printCommand }

    @TaskAction
    def runCommand() {
        if (printCommand) println command
        def p = Runtime.getRuntime().exec(command)
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line
        while ((line = input.readLine()) != null)
            println line
    }
}