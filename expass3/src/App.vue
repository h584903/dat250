<script setup>
import Poll from './components/Poll.vue'
import PollCreater from './components/PollCreater.vue'
</script>

<template>
  <div class="pageContainer">
    <Poll />
    <PollCreater />
  </div>
</template>

<style scoped>
.pageContainer {
  display: flex;
  flex-direction: column;
}
</style>
