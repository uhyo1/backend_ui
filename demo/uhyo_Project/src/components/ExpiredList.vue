<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const items = ref([])

onMounted(async () => {
  const res = await axios.get('http://localhost:8080/expired/all')
  items.value = res.data
})
</script>

<template>
  <div class="p-6 bg-gradient-to-b from-gray-900 to-gray-800 text-gray-200">
    <h2>Expired Password List</h2>
      <table class="min-w-full text-sm text-gray-200 bg-gray-900 border border-gray-700">
        <thead class="bg-gray-800 text-gray-300">
          <tr>
            <th class="px-4 py-2 border border-gray-700">ID</th>
            <th class="px-4 py-2 border border-gray-700">User ID</th>
            <th class="px-4 py-2 border border-gray-700">Access Info</th>
            <th class="px-4 py-2 border border-gray-700">Password</th>
            <th class="px-4 py-2 border border-gray-700">Create Time</th>
            <th class="px-4 py-2 border border-gray-700">Updated Time</th>
          </tr>
        </thead>

        <tbody>
          <tr
            v-for="item in items"
            :key="item.pswdExpiredId"
            class="hover:bg-gray-800 transition"
          >
            <td class="px-4 py-2 border border-gray-700">{{ item.pswdExpiredId }}</td>
            <td class="px-4 py-2 border border-gray-700">{{ item.userId }}</td>
            <td class="px-4 py-2 border border-gray-700">{{ item.accessInfo }}</td>
            <td class="px-4 py-2 border border-gray-700">{{ item.password }}</td>
            <td class="px-4 py-2 border border-gray-700">{{ item.createTime }}</td>
            <td class="px-4 py-2 border border-gray-700">{{ item.updatedTime }}</td>
          </tr>
        </tbody>
      </table>
  </div>
</template>

<style scoped>
.container {
  padding: 20px;
  font-family: sans-serif;
}

h2 {
  margin-bottom: 16px;
}

.table {
  width: 100%;
  border-collapse: collapse;
  background: #222;
  color: #eee;
}

.table th,
.table td {
  border: 1px solid #444;
  padding: 8px 12px;
  text-align: left;
}

.table th {
  background: #333;
  font-weight: bold;
}

.table tr:nth-child(even) {
  background: #2a2a2a;
}
</style>
