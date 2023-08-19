//
//  NoteDetailViewModel.swift
//  iosApp
//
//  Created by Adrian Lesniak on 19/08/2023.
//  Copyright © 2023 orgName. All rights reserved.
//
import Foundation
import shared

extension NoteDetailScreen {
    @MainActor class NoteDetailViewModel: ObservableObject {
        private var noteDataSource: NotesDataSource?
        
        private var noteId: Int64? = nil
        @Published var noteTitle = ""
        @Published var noteContent = ""
        @Published private(set) var noteColor = Note.Companion().randomColor()
        
        init(noteDataSource: NotesDataSource? = nil) {
            self.noteDataSource = noteDataSource
        }
        
        func loadNoteIfExists(id: Int64?) {
            if id != nil {
                self.noteId = id
                noteDataSource?.getNoteById(noteId: id!, completionHandler: { note, error in
                    self.noteTitle = note?.title ?? ""
                    self.noteContent = note?.content ?? ""
                    self.noteColor = note?.colorHex ?? Note.Companion().randomColor()
                })
            }
        }
        
        func saveNote(onSaved: @escaping () -> Void) {
            noteDataSource?.addOrUpdateNote(
                note: Note(id: noteId == nil ? nil : KotlinLong(value: noteId!), title: self.noteTitle, content: self.noteContent, colorHex: self.noteColor, createdAt: DateTimeUtilKt.now()), completionHandler: { error in
                    onSaved()
                })
        }
        
        func setParamsAndLoadNote(noteDataSource: NotesDataSource, noteId: Int64?) {
            self.noteDataSource = noteDataSource
            loadNoteIfExists(id: noteId)
        }
    }
}
