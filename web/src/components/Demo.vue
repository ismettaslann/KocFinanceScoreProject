<template>
    <div class="main_div">
        <b-card bg-variant="light">
            <form v-on:submit.prevent="calculateScore">
                <b-row class="my-1">
                    <b-col sm="3">
                        <label for="">Ad Soyad:</label>
                    </b-col>
                    <b-col sm="9">
                        <b-form-input type="text" id="nameAndSurname" v-model="form.nameAndSurname" required></b-form-input>
                    </b-col>
                </b-row>
                <b-row class="my-1">
                    <b-col sm="3">
                        <label for="identityNumber">Kimlik Numarası:</label>
                    </b-col>
                    <b-col sm="9">
                        <b-form-input :type="number" id="identityNumber" v-model="form.identityNumber" :maxlength="11" :placeholder="11111111111" required></b-form-input>
                    </b-col>
                </b-row>
                <b-row class="my-1">
                    <b-col sm="3">
                        <label for="phoneNumber">Cep Telefonu:</label>
                    </b-col>
                    <b-col sm="9">
                        <b-form-input :type="number" id="phoneNumber" v-model="form.phoneNumber" :maxlength="10" :placeholder="5551111111" required></b-form-input>
                    </b-col>
                </b-row>
                <b-row class="my-1">
                    <b-col sm="3">
                        <label for="incomeTranche">Gelir Dilimi :</label>
                    </b-col>
                    <b-col sm="9">
                        <b-form-select v-model="form.incomeTranche" :options="options" id="incomeTranche" required></b-form-select>
                    </b-col>
                </b-row>
                <b-row class="my-1">
                    <b-col sm="3">
                        <label for="residence">İkamet Ettiği İl :</label>
                    </b-col>
                    <b-col sm="9">
                        <b-form-select :options="cities" id="residence" v-model="form.residence" required></b-form-select>
                    </b-col>
                </b-row>
                <b-row class="text-right">
                    <b-col sm="3">
                    </b-col>
                    <b-col sm="9">
                        <b-button variant="outline-danger" type="submit" :disabled="inProgress">Skor Göster</b-button>
                    </b-col>
                </b-row>
            </form>
            <p v-if="errors.length">
                <ul>
                <li v-for="error in errors" :key="error">{{ error }}</li>
                </ul>
            </p>
            <b-row v-if="score">
                <b-col sm="12"> 
                    <label>Toplam Skor : {{ score }}</label>
                </b-col>
            </b-row>
        </b-card>
    </div>
</template>

<script>
import {post} from '../common/api-services'
import cities from '../json/cities.json'

export default {
    name: 'Demo',
    data() {
        return {
            form: {
                identityNumber:null,
                nameAndSurname: '',
                incomeTranche: null,
                residence:null,
                phoneNumber:null,
            },
            selected: null,
            options: [
                { value: null, text: 'Lütfen gelir dilimi seçiniz.'   },
                { value: '1',  text: '0-2999 TL'                      },
                { value: '2',  text: '3000TL-4999TL'                  },
                { value: '3',  text: '5000TL-7999TL'                  },
                { value: '4',  text: '8000TL-11999TL'                 },
                { value: '5',  text: '12000TL ve üzeri'               }
            ],
            cities: cities,
            show: false,
            inProgress : false,
            errors : []
        }
    },
    props : {
        score : null,
        
    },
    methods: {
        checkForm: function(){
            this.errors = [];
            this.score = null;
            if(this.form.incomeTranche == null){
                this.errors.push("Lütfen Gelir dilimi bilgisini doldurunuz!")
            }
            if(this.form.residence == null){
                this.errors.push("Lütfen İkamet Ettiği İl bilgisini doldurunuz!")
            }
            if(this.form.identityNumber.toString().length < 11){
                this.errors.push("Lütfen Kimlik Numarası bilgisini en az 11 hane olacak şekilde doldurunuz!")
            }
            if(this.form.identityNumber.toString().startsWith('0')){
                this.errors.push("Kimlik numarası 0 ile başlayamaz!")
            }
            if(this.form.phoneNumber.toString().length < 10){
                this.errors.push("Lütfen Telefon Numarası bilgisini en az 10 hane olacak şekilde doldurunuz!")
            }           

            if(this.errors.length > 0){
                return false;
            }
            return true;
        },
        calculateScore() {
            if(this.checkForm()){
                this.inProgress = true;
                post('score/insert-new-person-score/', this.form).then(response => {
                    console.log(response);
                    this.score = response.data.score;
                })
                .catch(error => {
                    console.log(error);
                    this.errors.push("Skor servisinde bir sorun ile karşılaşıldı, Lütfen tekrar deneyiniz.");
                })
                .finally(()=> (this.inProgress = false));
                this.show = true;
            }
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
label{
    float: left;
}
.main_div {
    margin-left: 25%;
    margin-right: 25%;
    text-align: center;
    display: block;
}
</style>
