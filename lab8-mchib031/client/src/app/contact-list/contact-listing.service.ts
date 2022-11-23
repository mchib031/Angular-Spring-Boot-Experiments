import { Injectable } from '@angular/core';
import { ApolloQueryResult, FetchResult } from '@apollo/client/core';
import { Apollo, gql } from 'apollo-angular';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ContactListingService {
  constructor(private apollo: Apollo) {}

  public getUsers(): Observable<ApolloQueryResult<any>> {
    return this.apollo.query<any>({
      query: gql`
        query {
          users {
            userId
            firstName
            lastName
            phoneNumber
            email
          }
        }
      `,
    });
  }

  public addUser(
    firstName: string,
    lastName: string,
    phoneNumber: string,
    email: string
  ): Observable<FetchResult<unknown>> {
    return this.apollo.mutate({
      mutation: gql`
        mutation newUser(
          $firstName: String!
          $lastName: String!
          $phoneNumber: String
          $email: String
        ) {
          newUser(
            firstName: $firstName
            lastName: $lastName
            email: $email
            phoneNumber: $phoneNumber
          ) {
            userId
          }
        }
      `,
      variables: {
        firstName: firstName,
        lastName: lastName,
        phoneNumber: phoneNumber,
        email: email,
      },
    });
  }
}
