**README**

1. **Compile the Code:** Use a Java compiler to compile the `TimingData.java` file.
    ```bash
    javac TimingData.java
    ```

2. **Run the Program:** Execute the compiled Java program.
    ```bash
    java TimingData
    ```

3. **Adjust Parameters:** Modify the following variables in the `TimingData.java` file for different experimental setups:
    - `NUM_RUNS`: Number of experimental runs (default: 10)
    - `NUM_ARRAYS`: Number of arrays per run (default: 100)
    - `MAX_ARRAY_SIZE`: Maximum size of the arrays (default: 100000)
    - `SIZE_INCREMENT`: Size increment for each subsequent array (default: 1000)
Modify the following variables in the `plot.py` file for different graph setups:
    - `NUM_RUNS`: Number of experimental runs (default: 10)
    - `INCREMENT`: Size increment for each subsequent array (default: 1000)

4. **View Results:** Check the generated `timing_data.txt` file for sorting times. Additionally, 'plot.py' can be used to graph the results.