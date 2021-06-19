import {Avatar} from "./Avatar.js";
import {goodAnswers} from "./goodAnswers.js";
export const people = {
    template:'' +
        '<div class="people-container flex justify-center m-4 flex-col items-center">' +
        '  <div class="info-container rounded-sm shadow w-full flex flex-row items-center">  ' +
        '    <Avatar/>' +
        '    <div class="name-school">' +
        '      <div class="flex flex-col ml-6 mt-20">' +
        '        <div class="people-name">' +
        '          {{this.$store.state.user.username}}' +
        '        </div>' +
        '        <div class="people-school mt-2">' +
        '          {{this.$store.state.user.school}}' +
        '        </div>' +
        '      </div>' +
        '  </div>' +
        '</div>' +
        '<div class="ques-container w-full shadow rounded-sm">' +
        '  <div class="i-like">' +
        '    我赞过的' +
        '  </div>' +
        '  <good-answers/>' +
        '</div> ' +
        '</div>',
    components:{ Avatar,goodAnswers }
}




