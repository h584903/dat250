import { defineStore } from "pinia";
import { ref } from 'vue';
import axios from 'axios';

export const usePollStore = defineStore("pollStore", {
  state: () => ({
    polls: [],
  }),
  getters: {
    getPolls(state){
      return state.polls
    }
  },
  actions: {
    async fetchPolls() {
      try {
        const data = await axios.get('http://localhost:8080/listPolls')
        console.log(data);
        this.polls = data.data
      } catch (error) {
        alert(error)
        console.log(error)
      }
    },
    async addPoll(poll) {
      try {
        // Send the poll data to the backend
        const response = await axios.post('http://localhost:8080/createPoll?username=tester', {
          question: poll.question,
          validUntil: "2024-12-31T23:59:59Z",  // Example fixed validUntil date
          voteOptions: poll.voteOptions
        });

        console.log(response.data);
        
        // If the poll was successfully created, you could store it in the state
        this.polls.push(response.data);  // Update the state with the new poll data

      } catch (error) {
        console.error("Error adding poll:", error);
        throw error;  // Rethrow the error to handle it elsewhere if needed
      }
    },
    async vote(id, optionId) {
      try {
        const data = await axios.post("http://localhost:8080/votePoll/" + id + "/vote?username=tester&voteOptionId=" + optionId)
        console.log(data)
      } catch (error) {
        alert(error)
        console.log(error)
      }
    }
  },
})
