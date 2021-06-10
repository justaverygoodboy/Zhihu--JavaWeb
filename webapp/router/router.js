import {myQuestions} from '../components/homepage/myQuestions.js'
import {questionDetail} from '../components/homepage/questionDetail.js';
import {people} from '../components/homepage/people.js'
export const routes = [
    {path:'/', component:myQuestions},
    {path:'/question/:quesId',component: questionDetail},
    {path:'/people/:userId',component: people},
]
