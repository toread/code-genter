package com.toread.code.config;
/**
 *模板配置
 */
public class GeneratorControl {
    private boolean isOverride = false;
    private boolean ignoreOutput = false;
    private String mergeLocation;
    private String outputFile;

    public boolean isOverride() {
        return isOverride;
    }

    public void setOverride(boolean isOverride) {
        this.isOverride = isOverride;
    }

    public boolean isIgnoreOutput() {
        return ignoreOutput;
    }

    public void setIgnoreOutput(boolean ignoreOutput) {
        this.ignoreOutput = ignoreOutput;
    }

    public String getMergeLocation() {
        return mergeLocation;
    }

    public void setMergeLocation(String mergeLocation) {
        this.mergeLocation = mergeLocation;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }
}
