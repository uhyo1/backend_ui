<template>
  <div class="task-container">
    <h2>作業リスト</h2>

    <div
  v-if="tooltip.visible"
  class="tooltip-fixed"
  :style="{ top: tooltip.y + 'px', left: tooltip.x + 'px' }"
>
  {{ tooltip.text }}
</div>


    <table class="task-table">
      <thead>
        <tr>
          <th>タスク</th>
          <th>優先度</th>
          <th>input_table</th>
          <th>開始日</th>
          <th>期日</th>
          <th>完了/期限切れ?</th>
          <th>メモ</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="(task, index) in tasks" :key="index">
          <td>
            <div
                @mouseover="task.showTooltip = true"
                @mouseleave="task.showTooltip = false"
                @mousemove="updateTooltip($event, task)"
  >
                    <input
                    type="text"
                    v-model="task.filename"
                    class="filename-input"
                    />
            </div>
          </td>

        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'

const tasks = ref([
  {
    name: 'タスク1',
    priority: '高',
    filename: 'report_2026_01.xlsx',
    start: '2026-11-29',
    end: '2026-12-08',
    showTooltip: false
  },
  {
    name: 'タスク2',
    priority: '中',
    filename: 'data_backup_01',
    start: '2026-11-19',
    end: '2026-12-19',
    showTooltip: false
  }
])

// tooltip の位置と内容
const tooltip = reactive({
  x: 0,
  y: 0,
  text: '',
  visible: false
})

function updateTooltip(e, task) {
  tooltip.x = e.clientX + 15
  tooltip.y = e.clientY + 15
  tooltip.text = task.filename
  tooltip.visible = task.showTooltip
}
</script>


<style scoped>
.task-container {
  padding: 20px;
}

.task-table {
  border-collapse: collapse;
  width: 100%;
}

.task-table th,
.task-table td {
  border: 1px solid #ccc;
  padding: 8px;
}
</style>
