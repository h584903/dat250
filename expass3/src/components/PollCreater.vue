<script setup>
    import { ref, onMounted, computed } from "vue";
    import { usePollStore } from "../store/polls/index";

    const store = usePollStore();

    let optioncount = ref(1);
    let options = ref([]);
    const pollTitle = ref('');

    function addOption(){
      optioncount.value++;
      console.log(optioncount.value)
    }

    async function submit() {
      try {
          // Prepare options for the store
          const formattedOptions = options.value.map((option, index) => ({
            caption: option,
            presentationOrder: index + 1,
          }));

          // Call the store action to add the poll
          await store.addPoll({
            question: pollTitle.value,  // The poll title
            voteOptions: formattedOptions,  // The formatted options
          });
          store.fetchPolls();

          console.log("Poll submitted successfully!");

        } catch (error) {
          console.error("Error submitting poll:", error);
        }
      }

</script>
<template>
  <div class="poll">
    <input v-model="pollTitle" placeholder="Title" />
    <div v-for="(option, index) in optioncount" :key="index">
      <input v-model="options[index]" placeholder="Caption" />
    </div>
    <button @click="addOption">Add Option</button>
    <button @click="submit">Submit</button>
  </div>
</template>
<style scoped>
.poll {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}
.option{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    width: 100%;
}
</style>
