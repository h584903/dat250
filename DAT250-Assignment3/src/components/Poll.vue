<script setup>
    import { ref, onMounted, computed } from "vue";
    import { usePollStore } from "../store/polls/index";
    const store = usePollStore();

    const polls = computed(() => {
        return store.polls;
    });

    onMounted(() => {
        console.log("Component mounted, fetching polls");
        store.fetchPolls();
    });
    async function vote(id, optionId) {
        store.vote(id, optionId)
        store.fetchPolls();
    }

</script>
<template>
    <div class="container" v-for="poll in polls" :key="poll.id">
        <div class="poll">
            <div class="prompt">{{ poll.question }}</div>
            <div class="options">
                <div v-for="option in poll.voteOptions" :key="option.optionId">
                    <div class="option">
                        <span class="optionPrompt">{{ option.caption }}</span>
                        <div class="voteOption">
                            <button @click="vote(poll.id, option.optionId)" class="voteButton">upvote</button>
                            <button @click="vote(poll.id, option.optionId)" class="voteButton">downvote</button>
                            <span>{{ option.votes.length }}</span>
                        </div>
                    </div>
                </div>
            </div> 
        </div>
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
