import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms'


@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {
  FormLogin: FormGroup;
  user: FormControl;
  password: FormControl
  fechaNa: FormControl | undefined

  constructor(){
    this.FormLogin = new FormGroup('')
    this.user = new FormControl('')
    this.password = new FormControl('')
    this.fechaNa = new FormControl('')


    this.FormLogin = new FormGroup({
      user: this.user,
      password: this.password,
      fechaNa: this.fechaNa
    })
  }

  NewUser(){
    console.log(this.FormLogin.value)
    this.FormLogin.reset()
  }
}
