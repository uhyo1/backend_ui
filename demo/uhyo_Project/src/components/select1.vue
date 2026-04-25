<template>
  <div class="p-4">
    <h2 class="text-xl font-bold mb-2">Customer Select</h2>

    <table class="table-auto border-collapse border border-gray-400 w-full mb-4">
      <thead>
        <tr class="bg-gray-200">
          <th class="border px-2 py-1">ID</th>
          <th class="border px-2 py-1">Name</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="c in customers" :key="c.customerId">
          <td class="border px-2 py-1">{{ c.customerId }}</td>
          <td class="border px-2 py-1">{{ c.name }}</td>
        </tr>
      </tbody>
    </table>

    <button
      @click="sendToBackend"
      class="bg-blue-500 text-white px-4 py-2 rounded"
    >
      Send to Backend
    </button>

    <div v-if="backendResult" class="mt-4 p-4 bg-gray-100 rounded">
      <pre>{{ backendResult }}</pre>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

// customer_ID 1〜3 の仮データ（本来は DB から取得する）
const customers = ref([
  { customerId: 1, name: "AAA" },
  { customerId: 2, name: "BBB" },
  { customerId: 3, name: "CCC" }
])

const backendResult = ref(null)

const sendToBackend = async () => {
  try {
    const response = await axios.post("http://localhost:8080/api/job", {
      customers: customers.value
    })
    backendResult.value = response.data
  } catch (e) {
    console.error(e)
  }
}
</script>
