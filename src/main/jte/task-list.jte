@param com.example.jte_htmx_task_manager.Task task

<tr>
	<td>${task.getDescription()}</td>
	<td>Pending</td>
	<td>
		<button
			hx-delete="delete-task/${task.getId()}"
			hx-target="closest tr"
			hx-swap="outerHTML swap:1s"
		>Delete, <span>${task.getDescription()}</span></button>
	</td>
</tr>
