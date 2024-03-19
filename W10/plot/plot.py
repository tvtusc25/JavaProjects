import matplotlib.pyplot as plt
import numpy as np

# Read data from the timing data file
with open('timing_data.txt', 'r') as file:
    lines = file.readlines()

# Data stripping and assignment
data = [line.strip().split(',') for line in lines]  # Strip ',' from lines to separate data
num_runs = 10 # number of runs
increment = 1000 # how array size increments
num_points = len(data) // num_runs
array_sizes = np.arange(1, num_points + 1) * increment # Array size for x-axis label

# Reshape data for easier handling
selection_sort_times = np.array([int(item[0]) for item in data]).reshape(-1, num_points)
merge_sort_times = np.array([int(item[1]) for item in data]).reshape(-1, num_points)

# Calculate mean for each array size
mean_selection_sort = np.mean(selection_sort_times, axis=0)
mean_merge_sort = np.mean(merge_sort_times, axis=0)

# Find the index where the lines intersect
intersection_index = np.argmax(mean_selection_sort > mean_merge_sort)

# Plot the graph with average lines
plt.plot(array_sizes, mean_selection_sort, label='Selection Sort')
plt.plot(array_sizes, mean_merge_sort, label='Merge Sort')
plt.scatter(array_sizes[intersection_index], mean_selection_sort[intersection_index], color='red', marker='o', label='Intersection')
plt.text(array_sizes[intersection_index], mean_selection_sort[intersection_index] + 50, f'Intersection\n({array_sizes[intersection_index]}, {mean_selection_sort[intersection_index]:.2f})', fontsize=8, ha='left')
plt.xlabel('Array Size')  # x-axis label
plt.ylabel('Time (milliseconds)')  # y-axis label
plt.title('Sorting Algorithm Performance')  # Title
plt.legend()  # Show legend
plt.show()
