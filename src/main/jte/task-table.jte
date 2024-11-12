@import java.util.List
@param List<String> tasks

<table>
	<thead>
		<th>Task Description</th>
		<th>Status</th>
		<th>Actions</th>
	<thead>
	<tbody>
		@for(int i = 0; i < tasks.size(); i++)
			<tr>
				<td>${tasks.get(i)}</td>
				<td>Pending</td>
				<td>
					<button
						hx-delete="delete-task/${i}"
						hx-target="#taskTable"
						hx-swap="innerHTML"
					>
						Delete, <span>Task ${id}</span> 
					</button>
				</td>
			</tr>
		@endfor
	</tbody>
</table>
