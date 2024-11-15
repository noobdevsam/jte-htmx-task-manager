@import com.example.jte_htmx_task_manager.Task;
@param java.util.List<Task> tasks

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Manager</title>
    <script src="https://unpkg.com/htmx.org@2.0.3/dist/htmx.js"
            integrity="sha384-BBDmZzVt6vjz5YbQqZPtFZW82o8QotoM7RUp5xOxV3nSJ8u2pSdtzFAbGKzTlKtg"
            crossorigin="anonymous"></script>
</head>
<body>
<div>
    <div>
        <div>
            <div>
                <div>
                    <h1>Task Manager</h1>
                    <p>A list of all your tasks including their description and
                        status.</p>
                </div>
                <div>
                    <form hx-post="/add-task"
                          hx-target="#taskTableBody"
                          hx-swap="beforeend"
                          hx-on::after-request="this.reset()">
                        <input id="newTaskInput"
                               type="text"
                               name="description"
                               placeholder="Enter a new task"
                               >
                        <button type="submit">
                            Add Task
                        </button>
                    </form>
                </div>
            </div>
            <div>
                <div>
                    <div>
                        <table>
                            <thead>
                            <tr>
                                <th scope="col">Task Description</th>
                                <th scope="col" >Status
                                </th>
                                <th scope="col" >
                                    <span>Actions</span>
                                </th>
                            </tr>
                            </thead>
                            <tbody id="taskTableBody" >
                            @for(Task task : tasks)
                                @template.task-row(task = task)
                            @endfor
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
