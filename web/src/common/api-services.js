import axios from 'axios'
import {BASE_URL} from '../common/config.js'

export async function post (path = '', params){
    try {
        console.log("params = " + params);
        return axios.post(BASE_URL + path, params);
    } catch (err) {
        console.log(err);
        throw new Error('Http post error on api-service.js');
    }
}
