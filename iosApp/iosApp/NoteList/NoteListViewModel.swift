//
//  NoteListViewModel.swift
//  iosApp
//
//  Created by Adrian Lesniak on 19/08/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension NoteListScreen {
    @MainActor class NoteListViewModel: ObservableObject {
        private var notesDataSource: NotesDataSource?
        
        private var searchNotes = SearchNotes()
        
        private var notes = [Note]()
        @Published private(set) var filteredNotes = [Note]()
        @Published var searchText = "" {
            didSet {
                self.filteredNotes = searchNotes.invoke(notes: self.notes, query: searchText)
            }
        }
        
        @Published private(set) var isSearchActive = false
        
        init(notesDataSource: NotesDataSource? = nil) {
            self.notesDataSource = notesDataSource
        }
        
        func setNotesDataSource(notesDataSource: NotesDataSource) {
            self.notesDataSource = notesDataSource
        }
        
        func loadNotes() {
            notesDataSource?.getNotes(completionHandler: { notes, error in
                self.notes = notes ?? []
                self.filteredNotes = self.notes
            })
        }
        
        func deleteNoteById(id: Int64?) {
            if id != nil {
                notesDataSource?.removeNote(noteId: id!, completionHandler: { error in
                    self.loadNotes()
                })
            }
        }
        
        func toggleIsSearchActive() {
            isSearchActive = !isSearchActive
            if !self.isSearchActive {
                searchText = ""
            }
        }
    }
}
