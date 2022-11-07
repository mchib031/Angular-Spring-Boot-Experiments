import { Pipe, PipeTransform } from '@angular/core';
import {Author} from '../books/model/book';

@Pipe({
  name: 'authornames'
})
export class AuthornamesPipe implements PipeTransform {

  transform(value: Author[] | undefined): string {
    if (value == null) return '';
    return value.map((author) => `${author.firstName}, ${author.lastName}`).join(' <b>and</b> ');
  }

}
